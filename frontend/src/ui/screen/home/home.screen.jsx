import { useCallback, useEffect, useState } from 'react'
import { Menu, ModalColecao } from '@components'
import { RoutesConfigGlobal } from '@contexts'
import { UseAuthentication, UseCollection } from '@hooks'
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
  const { getColletion } = UseCollection()

  const getStickerAutenticacao = useCallback(async () => {
    const resultado = await getControleAutenticacao()

    if (resultado?.mensagem) {
      setColecao(resultado)
      setOpenModal(true)
    } else {
      if (colecao?.mensagem) {
        const colecao = await getColletion()

        setColecao(colecao)
      }
    }
  }, [getControleAutenticacao])

  useEffect(() => {
    getStickerAutenticacao()
  }, [getStickerAutenticacao])

  const handleClose = () => {
    setOpenModal(false)
  }

  const handleClickModal = async () => {
    const resultado = await getColletion()

    setColecao(resultado)
    setOpenModal(true)
  }

  return (
    <section className="home">
      <div className="home__container">
        <main className="home__main">{ScreenComponents[routesConfig]}</main>
        <Menu className="home__menu" handleClickModal={handleClickModal} />
      </div>
      <ModalColecao open={openModal} handleClose={handleClose} colecao={colecao} />
    </section>
  )
}

export { HomeScreen }
