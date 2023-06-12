import { useGlobalToken } from '@contexts'
import { UseAuthentication } from '@hooks'
import { LocalFireDepartment } from '@mui/icons-material'
import { useCallback, useEffect, useState } from 'react'

import './footer.style.scss'

const Footer = () => {
  const [dias, setDias] = useState()
  const [mostrar, setMostrar] = useState()
  const [token] = useGlobalToken()

  const { getDiasAutenticacao } = UseAuthentication()

  useEffect(() => {
    setMostrar(!!token)
  }, [token])

  const getDiasAutenticacaoApi = useCallback(async () => {
    const qntDias = await getDiasAutenticacao()
    setDias(qntDias)
  }, [getDiasAutenticacao])

  useEffect(() => {
    getDiasAutenticacaoApi()
  }, [getDiasAutenticacaoApi])

  return (
    <footer className="footer">
      {mostrar ? (
        <div className="footer__config">
          <LocalFireDepartment sx={{ fontSize: 29 }} color="primary" />
          <div className="footer__dias">12 dias</div>
        </div>
      ) : null}
    </footer>
  )
}

export { Footer }
