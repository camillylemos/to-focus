/* eslint-disable react-hooks/exhaustive-deps */
import { useMemo } from 'react'
import { useAxios } from './use-axios'

const UseAuthentication = () => {
  const { get } = useAxios()

  const getControleAutenticacao = async () => await get('/autenticacao/controle')

  const getDiasAutenticacao = async () => await get('/autenticacao')

  return useMemo(
    () => ({
      getControleAutenticacao,
      getDiasAutenticacao,
    }),
    []
  )
}

export { UseAuthentication }
