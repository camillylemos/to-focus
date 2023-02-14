import { useCallback, useEffect, useState } from 'react'
import { Menu } from '@components'
import { RoutesConfigGlobal } from '@contexts'
import { UseAuthentication } from '@hooks'
import { Dialog } from '@mui/material'
import { EisenhowerMatrixScreen } from '../eisenhower-matrix/eisenhower-matrix.screen'
import { PomodoroScreen } from '../pomodoro/pomodoro.screen'
import { TaskScreen } from '../task/task.screen'
import { RelatorioScreen } from '../relatorio/relatorio.screen'

import './home.style.scss'

const ScreenComponents = {
  PomodoroScreen: <PomodoroScreen />,
  TaskScreen: <TaskScreen />,
  EisenhowerMatrixScreen: <EisenhowerMatrixScreen />,
  RelatorioScreen: <RelatorioScreen />,
}

const HomeScreen = () => {
  const [colecao, setColecao] = useState()
  const [openModal, setOpenModal] = useState(false)
  const [routesConfig] = RoutesConfigGlobal()

  const { getControleAutenticacao } = UseAuthentication()

  const getStickerAutenticacao = useCallback(async () => {
    const resultado = await getControleAutenticacao()

    if (resultado?.mensagem) {
      setColecao({ mensagem: resultado.mensagem })
      setOpenModal(true)
    }
  }, [getControleAutenticacao])

  useEffect(() => {
    getStickerAutenticacao()
  }, [getStickerAutenticacao])

  const handleClose = () => {
    setOpenModal(false)
  }

  return (
    <section className="home">
      <div className="home__container">
        <main className="home__main">{ScreenComponents[routesConfig]}</main>
        <Menu className="home__menu" />
      </div>

      <Dialog open={openModal} onClose={handleClose}>
        {colecao?.mensagem}
      </Dialog>
    </section>
  )
}

export { HomeScreen }
