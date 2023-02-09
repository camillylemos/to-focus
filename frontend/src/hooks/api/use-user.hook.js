/* eslint-disable react-hooks/exhaustive-deps */
import { useMemo } from 'react'
import { useAxios } from './use-axios'

const UseUser = () => {
  const { post } = useAxios()
  const login = async data => await post('/usuario/login', data)

  const register = data => post(`/usuario/cadastrar`, data)

  return useMemo(
    () => ({
      register,
      login,
    }),
    []
  )
}

export { UseUser }
