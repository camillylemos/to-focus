import { album, graphic, listCheck, matrix, note } from '@assets'
import { RoutesConfigGlobal } from '@contexts'

import './menu.style.scss'

const Menu = () => {
  const [, setRoutesConfig] = RoutesConfigGlobal()

  const handleClick = screen => {
    setRoutesConfig(screen)
  }

  return (
    <aside className="menu">
      <button onClick={() => handleClick('PomodoroScreen')} className="button menu__button">
        <img className="menu__button__icon" src={note} alt="" />
      </button>

      <button className="button menu__button">
        <img className="menu__button__icon" src={album} alt="" />
      </button>

      <button onClick={() => handleClick('TaskScreen')} className="button menu__button">
        <img className="menu__button__icon" src={listCheck} alt="" />
      </button>

      <button className="button menu__button">
        <img className="menu__button__icon" src={graphic} alt="" />
      </button>

      <button onClick={() => handleClick('EisenhowerMatrixScreen')} className="button menu__button">
        <img className="menu__button__icon" src={matrix} alt="" />
      </button>
    </aside>
  )
}

export { Menu }
