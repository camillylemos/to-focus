import './chip.style.scss'

const Chip = ({ type, className }) => {
  console.log(type)
  return (
    <span className={`chip chip__${type.class} ${className}`}>
      <img className="chip__icon" src={type.icon.src} alt={type.icon.alt} />
      <span className="chip__texto">{type.text}</span>
    </span>
  )
}

export { Chip }
