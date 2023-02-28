/* eslint-disable react-hooks/exhaustive-deps */
import { useMemo } from 'react'
import { useAxios } from './use-axios'

const UseCollection = () => {
  const { get } = useAxios()

  const getColletion = () => get('/colecao')

  return useMemo(
    () => ({
      getColletion,
    }),
    []
  )
}

export { UseCollection }
