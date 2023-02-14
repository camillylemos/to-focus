import { Menu } from '@components'
import { RoutesConfigGlobal } from '@contexts'
import { UseAuthentication } from '@hooks'
import { Dialog } from '@mui/material'
import { useCallback, useEffect, useState } from 'react'
import { EisenhowerMatrixScreen } from '../eisenhower-matrix/eisenhower-matrix.screen'
import { PomodoroScreen } from '../pomodoro/pomodoro.screen'
import { TaskScreen } from '../task/task.screen'

import './home.style.scss'

const ScreenComponents = {
  PomodoroScreen: <PomodoroScreen />,
  TaskScreen: <TaskScreen />,
  EisenhowerMatrixScreen: <EisenhowerMatrixScreen />,
}

const comoEsta = {
  mensagem: 'Autenticacao numero 2',
  colecao: [
    {
      id: 1,
      album: { id: 1, nome: 'ALBUM_1' },
      figura: { id: 1, nome: 'FRUTA_1', isPremium: false },
    },
    {
      id: 2,
      album: { id: 1, nome: 'ALBUM_1' },
      figura: { id: 2, nome: 'FRUTA_2', isPremium: false },
    },
    {
      id: 3,
      album: { id: 1, nome: 'ALBUM_1' },
      figura: { id: 3, nome: 'FRUTA_3', isPremium: false },
    },
  ],
}

const comoDeveria = {
  mensagem: 'Autenticacao numero 2',
  colecao: [
    {
      nome: 'ALBUM_1',
      figuras: [
        { id: 1, nome: 'FRUTA_1', isPremium: false },
        { id: 2, nome: 'FRUTA_2', isPremium: false },
        { id: 3, nome: 'FRUTA_3', isPremium: false },
      ],
    },
  ],
}

const HomeScreen = () => {
  const [colecao, setColecao] = useState()
  const [openModal, setOpenModal] = useState()
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
        <main>{ScreenComponents[routesConfig]}</main>
        <Menu />
      </div>

      <Dialog open={openModal} onClose={handleClose}>
        {colecao?.mensagem}
      </Dialog>
    </section>
  )
}

export { HomeScreen }
