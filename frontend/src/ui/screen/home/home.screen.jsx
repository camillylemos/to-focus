import { Menu } from '@components'
import { EisenhowerMatrixScreen, TaskScreen } from '../'

import './home.style.scss'

const HomeScreen = () => {
  return (
    <section className="home">
      <div className="home__container">
        <main>
          {/* <EisenhowerMatrixScreen /> */}

          <TaskScreen />
        </main>

        <Menu />
      </div>
    </section>
  )
}

export { HomeScreen }
