import { Menu } from '@components'
import { RoutesConfigGlobal } from '@contexts'
import { EisenhowerMatrixScreen, PomodoroScreen, TaskScreen } from '../'

import './home.style.scss'

const ScreenComponents = {
  PomodoroScreen: <PomodoroScreen />,
  TaskScreen: <TaskScreen />,
  EisenhowerMatrixScreen: <EisenhowerMatrixScreen />,
}

const HomeScreen = () => {
  const [routesConfig] = RoutesConfigGlobal()

  return (
    <section className="home">
      <div className="home__container">
        <main>{ScreenComponents[routesConfig]}</main>
        <Menu />
      </div>
    </section>
  )
}

export { HomeScreen }
