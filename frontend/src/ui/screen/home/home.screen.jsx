import { Menu } from '@components'
import { RoutesConfigGlobal } from '@contexts'
import { EisenhowerMatrixScreen } from '../eisenhower-matrix/eisenhower-matrix.screen'
import { PomodoroScreen } from '../pomodoro/pomodoro.screen'
import { TaskScreen } from '../task/task.screen'

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
