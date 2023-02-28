import { useGlobalToken } from '@contexts'
import { UseAuthentication } from '@hooks'
import { LocalFireDepartment } from '@mui/icons-material'
import { useCallback, useEffect, useState } from 'react'

import './footer.style.scss'

const Footer = () => {
  const [dias, setDias] = useState()
  const [token] = useGlobalToken()

  const { getDiasAutenticacao } = UseAuthentication()

  const getDiasAutenticacaoApi = useCallback(async () => {
    const qntDias = await getDiasAutenticacao()
    setDias(qntDias)
  }, [getDiasAutenticacao])

  useEffect(() => {
    getDiasAutenticacaoApi()
  }, [getDiasAutenticacaoApi])

  return (
    <footer className="footer">
      {token ? (
        <div className="footer__config">
          <LocalFireDepartment sx={{ fontSize: 29 }} color="primary" />
          <div className="footer__dias">{dias} dias</div>
        </div>
      ) : null}
    </footer>
  )
}

export { Footer }
