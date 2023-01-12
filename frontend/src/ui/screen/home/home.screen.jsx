import { Menu } from '../../components'
import { PomodoroScreen } from '../pomodoro/pomodoro.screen'

import './home.style.scss'

const HomeScreen = () => {
  return (
    <section className="home">
      <div className="home__container">
        <main>
          <PomodoroScreen />
        </main>

        <Menu />
      </div>
    </section>
  )
}

export { HomeScreen }
