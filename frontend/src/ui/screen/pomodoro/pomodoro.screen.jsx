import { useEffect, useState, useCallback } from 'react'
// import { CircularProgress } from '@mui/joy'; //circulo pomodoro
import { useTimer } from 'react-timer-hook'
import { Button, IconButton } from '@mui/material'
import { usePomodoro } from '@hooks'
import { formatDigit } from '@utils'
import { POMODORO_STATUS } from '@constants'
import { FORM_DATA_INITIAL, ModalComponent } from './partials'
import PlayArrowIcon from '@mui/icons-material/PlayArrow'
import RefreshIcon from '@mui/icons-material/Refresh';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';



import './pomodoro.style.scss'

const PomodoroScreen = () => {
  const [status, setStatus] = useState(POMODORO_STATUS.INITIAL)
  const [formData, setFormData] = useState({ ...FORM_DATA_INITIAL })
  const [pomodoroSettingsList, setPomodoroSettingsList] = useState()
  const [pomodoroId, setPomodoroId] = useState()
  const [pomodoroSelected, setPomodoroSelected] = useState()
  const [pomodoroActive, setPomodoroActive] = useState()
  const [pomodoroSettings, setPomodoroSettings] = useState({
    focus: 1,
    shortBreak: 0,
    longBreak: 0,
    allPomodoro: false,
  }) // refazer essa lógica TODO

  const {
    getPomodoroConfig,
    createPomodoroConfig,
    deletePomodoroConfig,
    startPomodoro,
    finishPomodoro,
  } = usePomodoro()
  const { hours, seconds, minutes, start, pause, restart } = useTimer({
    autoStart: false,
    expiryTimestamp: new Date(),
    onExpire: () => timeActive(),
  })

  const restartInitial = () => {
    setStatus(POMODORO_STATUS.INITIAL)
    setPomodoroActive({ titulo: 'FOCO', tempo: pomodoroSelected.tempoFoco })
  }

  const endPomodoro = async () => {
    await finishPomodoro(pomodoroId)
  }

  const timeActive = () => {
    if (pomodoroSettings.allPomodoro) {
      restartInitial()
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

  const getPomodoroConfigList = useCallback(async () => {
    const resultado = await getPomodoroConfig()
    setPomodoroSettingsList(resultado)
  }, [getPomodoroConfig])

  useEffect(() => {
    getPomodoroConfigList()
  }, [getPomodoroConfigList])

  useEffect(() => {
    if (!pomodoroSelected && pomodoroSettingsList) {
      setPomodoroSelected(pomodoroSettingsList[0])
      setPomodoroActive({ titulo: 'FOCO', tempo: pomodoroSettingsList[0].tempoFoco })
    }
  }, [pomodoroSelected, pomodoroSettingsList])

  const countdown = useCallback(time => {
    const newDate = new Date()
    return newDate.setSeconds(newDate.getSeconds() + time * 60)
  }, [])

  useEffect(() => {
    if (pomodoroActive) {
      const date = countdown(pomodoroActive.tempo)

      if (status === POMODORO_STATUS.INITIAL) {
        restart(date, false)
      } else {
        restart(date, true)
      }
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [pomodoroActive, countdown])

  const handleClickStartPomodoroAfterPause = () => {
    const newDate = new Date()
    newDate.setSeconds(newDate.getSeconds() + seconds)
    newDate.setMinutes(newDate.getMinutes() + minutes)
    newDate.setHours(newDate.getHours() + hours)
    restart(newDate, true)
  }

  const handleClickStartPomodoro = async () => {
    if (status === POMODORO_STATUS.PAUSE) {
      handleClickStartPomodoroAfterPause()
    } else {
      const resultado = await startPomodoro(pomodoroSelected.id)

      if (resultado) {
        setPomodoroId(resultado.id)
        start()
      }
    }

    setStatus(POMODORO_STATUS.PROGRESS)
  }

  const handleClickPausePomodoro = async () => {
    setStatus(POMODORO_STATUS.PAUSE)
    pause()
  }

  const handleClickRestartPomodoro = async () => {
    setStatus(POMODORO_STATUS.INITIAL)
    restartInitial()
  }

  const handleSubmit = async ({ isValid, values }) => {
    if (isValid && values) {
      const data = {
        nomeCategoria: values.name,
        tempoFoco: values.pomodoro,
        tempoIntervaloCurto: values.shortBreak,
        tempoIntervaloLongo: values.longBreak,
      }

      await createPomodoroConfig(data)
      getPomodoroConfigList()
      setFormData({ ...FORM_DATA_INITIAL })
    }
  }

  const handleClickDelete = async id => {
    await deletePomodoroConfig(id)
    getPomodoroConfigList()
  }

  const handleClick = ({
    id,
    nomeCategoria,
    tempoFoco,
    tempoIntervaloCurto,
    tempoIntervaloLongo,
  }) => {
    setPomodoroSelected({ id, nomeCategoria, tempoFoco, tempoIntervaloCurto, tempoIntervaloLongo })
    setPomodoroActive({ titulo: 'FOCO', tempo: tempoFoco })
    setStatus(POMODORO_STATUS.INITIAL)
  }

  const handleChange = event => {
    const { name, value } = event.target

    setFormData(formData => ({
      ...formData,
      [name]: {
        ...formData[name],
        value,
        name,
      },
    }))
  }

  return (
    <>
      <section className="pomodoro">
        <main>
          {!!hours && (
            <>
              <span>{formatDigit(hours)}</span>:
            </>
          )}
          <span>{formatDigit(minutes)}</span>:<span>{formatDigit(seconds)}</span>
        </main>
        <div>{pomodoroActive?.titulo}</div>


        <Button 
          variant="contained" 
          onClick={handleClickPausePomodoro}
          sx={{           
            width: 80,
            height: 80,
            borderRadius: 5,
            opacity: 0.25,
          }}
          color="secondary"
        >
          {<MoreHorizIcon className='dots__icon'/>}
        </Button>



         <Button 
          variant="contained" 
          onClick={handleClickStartPomodoro}
          sx={{
            width: 128,
            height: 96,
            borderRadius: 5,
            margin: 1,
          }}
          color="secondary"
        > 
        {<PlayArrowIcon className='play__icon'/>} 
        </Button> 

        <Button 
          variant="contained" 
          onClick={handleClickRestartPomodoro}
          sx={{
            width: 80,
            height: 80,
            borderRadius: 5,
            opacity: 0.25,
          }}
          color="secondary"
        >
        {<RefreshIcon className='refresh__icon'/>} 
        </Button>
      </section>

      <ModalComponent
        formData={formData}
        handleSubmit={handleSubmit}
        handleChange={handleChange}
        handleClick={handleClick}
        handleClickDelete={handleClickDelete}
        pomodoroSettingsList={pomodoroSettingsList}
      />
    </>
  )
}

export { PomodoroScreen }
