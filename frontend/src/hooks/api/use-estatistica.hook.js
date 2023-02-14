/* eslint-disable react-hooks/exhaustive-deps */
import { useMemo } from 'react'
import { useAxios } from './use-axios'

const UseEstatistica = () => {
  const { get } = useAxios()

  const getEstatistica = () => get('/estatistica')

  return useMemo(
    () => ({
      getEstatistica,
    }),
    []
  )
}

export { UseEstatistica }
