import { useEffect, useState, useCallback } from 'react'
import { useTimer } from 'react-timer-hook'
import { usePomodoro } from '../../../hooks'
import { formatDigit } from '../../../utils'
import { POMODORO_STATUS } from '../../../constants'
//import { CircularProgress } from '@mui/joy'; //circulo pomodoro

import './pomodoro.style.scss'

const PomodoroScreen = () => {
  const [status, setStatus] = useState(POMODORO_STATUS.INITIAL)
  const [pomodoroSettingsList, setPomodoroSettingsList] = useState()
  const [pomodoroSettings, setPomodoroSettings] = useState({
    focus: 1,
    shortBreak: 0,
    longBreak: 0,
    allPomodoro: false,
  })
  const [pomodoroSelected, setPomodoroSelected] = useState()
  const [pomodoroSelectedData, setPomodoroSelectedData] = useState()
  const [pomodoroActive, setPomodoroActive] = useState(POMODORO_STATUS.INITIAL)

  const endPomodoro = async () => {
    const resultado = await finishPomodoro(25)
    //TODO
    //add se resultado deu certo

    setStatus(POMODORO_STATUS.INITIAL)
  }

  const timeActive = () => {
    if (pomodoroSettings.allPomodoro) {
      return endPomodoro()
    }

    if (
      pomodoroSettings.focus === 4 &&
      pomodoroSettings.shortBreak === 3 &&
      pomodoroSettings.longBreak === 0
    ) {
      setPomodoroSettings({ ...pomodoroSettings, allPomodoro: true })
      return setPomodoroActive({
        titulo: 'INTERVALO_LONGO',
        tempo: pomodoroSelected.tempoIntervaloLongo,
      })
    }

    if (pomodoroSettings.focus > pomodoroSettings.shortBreak) {
      setPomodoroSettings({ ...pomodoroSettings, shortBreak: pomodoroSettings.shortBreak + 1 })
      return setPomodoroActive({
        titulo: 'INTERVALO_CURTO',
        tempo: pomodoroSelected.tempoIntervaloCurto,
      })
    }

    if (pomodoroSettings.shortBreak >= pomodoroSettings.focus) {
      setPomodoroSettings({ ...pomodoroSettings, focus: pomodoroSettings.focus + 1 })
      return setPomodoroActive({ titulo: 'FOCO', tempo: pomodoroSelected.tempoFoco })
    }
  }

  const { getPomodoroConfig, startPomodoro, finishPomodoro } = usePomodoro()
  const { seconds, minutes, start, pause, restart } = useTimer({
    autoStart: false,
    expiryTimestamp: pomodoroSelectedData,
    onExpire: () => timeActive(),
  })

  useEffect(() => {
    const getPomodoroConfigInitial = async () => {
      const resultado = await getPomodoroConfig()

      if (resultado) {
        setPomodoroSelected(resultado[0])
        setPomodoroActive({ titulo: 'FOCO', tempo: resultado[0].tempoFoco })
        setPomodoroSettingsList(resultado)
      }
    }

    getPomodoroConfigInitial()
  }, [getPomodoroConfig])

  const countdown = useCallback(time => {
    const newDate = new Date()
    return newDate.setSeconds(newDate.getSeconds() + time * 60)
  }, [])

  useEffect(() => {
    if (pomodoroSelected) {
      const date = countdown(pomodoroActive.tempo)

      setPomodoroSelectedData(date)

      if (status === POMODORO_STATUS.INITIAL) {
        restart(date, false)
      } else {
        restart(date, true)
      }
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [pomodoroSelected, pomodoroActive, status, countdown])

  const handleClickStartPomodoro = async () => {
    if (status === POMODORO_STATUS.PAUSE) {
      start()
      setStatus(POMODORO_STATUS.PROGRESS)
    } else {
      const resultado = await startPomodoro(pomodoroSelected.id)

      //TODO
      //add se resultado deu certo

      start()
      setStatus(POMODORO_STATUS.PROGRESS)
    }
  }

  const handleClickPausePomodoro = async () => {
    setStatus(POMODORO_STATUS.PAUSE)
    pause()
  }

  const handleClickRestartPomodoro = async () => {
    setStatus(POMODORO_STATUS.INITIAL)
    restart(countdown(), false)
  }

  return (
    <section className="pomodoro">
      <main>
        <span>{formatDigit(minutes)}</span>:<span>{formatDigit(seconds)}</span>
      </main>

      <div>{pomodoroActive?.titulo}</div>

      <button onClick={handleClickStartPomodoro}>Iniciar</button>
      <button onClick={handleClickPausePomodoro}>Pausar</button>
      <button onClick={handleClickRestartPomodoro}>Reiniciar</button>
    </section>
  )
}

export { PomodoroScreen }
