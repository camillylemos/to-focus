import { useEffect, useState, useCallback } from 'react'
import { useTimer } from 'react-timer-hook'
import { buildStyles, CircularProgressbarWithChildren } from 'react-circular-progressbar'
import { PlayArrow, Refresh, MoreHoriz, Pause } from '@mui/icons-material'
import { Button, Dialog } from '@mui/material'
import { POMODORO_STATUS, TYPES_CHIPS } from '@constants'
import { usePomodoro } from '@hooks'
import { formatDigit } from '@utils'
import { Chip } from '@components'
import { FORM_DATA_INITIAL, ModalComponent } from './partials'

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
  const [colecao, setColecao] = useState()
  const [openModal, setOpenModal] = useState()
  const [openModalSettings, setOpenModalSettings] = useState()
  const [value, setValue] = useState(0)

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
    const resultado = await finishPomodoro(pomodoroId)

    if (resultado?.mensagem) {
      setColecao({ mensagem: resultado.mensagem })
      setOpenModal(true)
    }
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

    if (resultado.length) {
      setPomodoroSettingsList(resultado)
    } else {
      setPomodoroSettingsList([
        {
          id: 1,
          nomeCategoria: 'PADRAO',
          tempoFoco: 25,
          tempoIntervaloCurto: 5,
          tempoIntervaloLongo: 15,
        },
      ])
    }
  }, [getPomodoroConfig])

  useEffect(() => {
    getPomodoroConfigList()
  }, [getPomodoroConfigList])

  useEffect(() => {
    if (!pomodoroSelected && pomodoroSettingsList) {
      setPomodoroSelected({
        id: 1,
        nomeCategoria: 'PADRAO',
        tempoFoco: 25,
        tempoIntervaloCurto: 5,
        tempoIntervaloLongo: 15,
      })
      setPomodoroActive({ titulo: 'FOCO', tempo: 25 })
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

  useEffect(() => {
    if (pomodoroActive) {
      const tempoAtual = hours * 3600 + minutes * 60 + seconds

      const valorAtual = Math.abs((100 * tempoAtual) / (pomodoroActive.tempo * 60) - 100)

      setValue(valorAtual)
    }
  }, [pomodoroActive, seconds, hours, minutes])

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
    setOpenModalSettings(false)
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

  const handleClose = () => {
    setOpenModal(false)
  }

  const handleClickModalSettings = () => {
    setOpenModalSettings(!openModalSettings)
  }

  const buttonConfig =
    status === POMODORO_STATUS.PROGRESS
      ? { onClick: handleClickPausePomodoro, icon: <Pause className="pause__icon" /> }
      : { onClick: handleClickStartPomodoro, icon: <PlayArrow className="play__icon" /> }

  return (
    <>
      <section className="pomodoro">
        <main className="pomodoro__progress-bar">
          <CircularProgressbarWithChildren
            value={value}
            strokeWidth={5}
            styles={buildStyles({
              pathColor: '#F29166',
              trailColor: '#2E7F7B',
              strokeLinecap: 'round',
            })}
          >
            <div className="pomodoro__timer__text">
              {!!hours && <span>{formatDigit(hours)}:</span>}
              <span>{formatDigit(minutes)}</span>:<span>{formatDigit(seconds)}</span>
            </div>
          </CircularProgressbarWithChildren>
        </main>

        {/* <div>{pomodoroActive?.titulo}</div> */}

        <div className="pomodoro__ciclo">
          <Chip type={TYPES_CHIPS.FOCUS} />
          <Chip type={TYPES_CHIPS.SHORT_BREAK} />
          <Chip type={TYPES_CHIPS.LONG_BREAK} />
        </div>

        <div>
          <Button
            variant="contained"
            onClick={handleClickModalSettings}
            sx={{
              width: 80,
              height: 80,
              borderRadius: 5,
              opacity: 0.25,
            }}
            color="secondary"
          >
            {<MoreHoriz className="dots__icon" />}
          </Button>

          <Button
            variant="contained"
            onClick={buttonConfig.onClick}
            sx={{
              width: 128,
              height: 96,
              borderRadius: 5,
              margin: 1,
            }}
            color="secondary"
          >
            {buttonConfig.icon}
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
            {<Refresh className="refresh__icon" />}
          </Button>
        </div>
      </section>

      <ModalComponent
        formData={formData}
        handleSubmit={handleSubmit}
        handleChange={handleChange}
        handleClick={handleClick}
        handleClickDelete={handleClickDelete}
        handleClickClose={handleClickModalSettings}
        pomodoroSettingsList={pomodoroSettingsList}
        open={openModalSettings}
      />

      <Dialog open={openModal} onClose={handleClose}>
        {colecao?.mensagem}
      </Dialog>
    </>
  )
}

export { PomodoroScreen }
