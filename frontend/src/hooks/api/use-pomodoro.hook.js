/* eslint-disable react-hooks/exhaustive-deps */
import { useMemo } from 'react'
import { useAxios } from './use-axios'

const usePomodoro = () => {
  const { get, post, put, del } = useAxios()

  const getPomodoroConfig = () => get('/pomodoro')

  const createPomodoroConfig = data => post('/pomodoro', data)

  const deletePomodoroConfig = id => del(`/pomodoro/${id}`)

  const startPomodoro = id => post(`/pomodoro/iniciar/${id}`)

  const finishPomodoro = id => put(`/pomodoro/finalizar/${id}`)

  return useMemo(
    () => ({
      getPomodoroConfig,
      createPomodoroConfig,
      deletePomodoroConfig,
      startPomodoro,
      finishPomodoro,
    }),
    []
  )
}

export { usePomodoro }
