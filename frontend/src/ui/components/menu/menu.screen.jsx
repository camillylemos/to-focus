import './menu.style.scss'
import { album, graphic, listCheck, matrix, note } from '../../assets'

const Menu = () => {
  return (
    <aside className="menu">
      <button className="button menu__button">
        <img className="menu__button__icon" src={note} alt="" />
      </button>

      <button className="button menu__button">
        <img className="menu__button__icon" src={album} alt="" />
      </button>

      <button className="button menu__button">
        <img className="menu__button__icon" src={listCheck} alt="" />
      </button>

      <button className="button menu__button">
        <img className="menu__button__icon" src={graphic} alt="" />
      </button>

      <button className="button menu__button">
        <img className="menu__button__icon" src={matrix} alt="" />
      </button>
    </aside>
  )
}

export { Menu }
