/* eslint-disable react-hooks/exhaustive-deps */
import { useMemo } from 'react'
import { useAxios } from './use-axios'

const UseAuthentication = () => {
  const { get } = useAxios()

  const getControleAutenticacao = async () => await get('/autenticacao/controle')

  return useMemo(
    () => ({
      getControleAutenticacao,
    }),
    []
  )
}

export { UseAuthentication }
