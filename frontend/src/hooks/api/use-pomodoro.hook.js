/* eslint-disable react-hooks/exhaustive-deps */
import { useMemo } from 'react'
import { useAxios } from './use-axios'

const usePomodoro = () => {
  const { get, post } = useAxios()

  const getPomodoroConfig = () => get('/pomodoro')

  const startPomodoro = id => post(`/pomodoro/iniciar/${id}`)

  const finishPomodoro = id => post(`/pomodoro/finalizar/${id}`)

  return useMemo(
    () => ({
      getPomodoroConfig,
      startPomodoro,
      finishPomodoro,
    }),
    []
  )
}

export { usePomodoro }
