import { Menu } from '@components'
import { EisenhowerMatrixScreen, TaskScreen, PomodoroScreen} from '../'

import './home.style.scss'

const HomeScreen = () => {
  return (
    <section className="home">
      <div className="home__container">
        <main>
          {/* <EisenhowerMatrixScreen /> */}
<PomodoroScreen/>
          <TaskScreen />
        </main>

        <Menu />
      </div>
    </section>
  )
}

export { HomeScreen }
