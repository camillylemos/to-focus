import { break_, focus } from '@assets'

const TYPES_CHIPS = {
  LONG_BREAK: {
    icon: { src: break_, alt: 'Ícone de um café.' },
    text: 'Intervalo Longo',
    class: 'long-break',
  },
  FOCUS: {
    icon: {
      src: focus,
      alt: 'Ícone de um cérebro.',
    },
    text: 'Foco',
    class: 'focus',
  },
  SHORT_BREAK: {
    icon: { src: break_, alt: 'Ícone de um café.' },
    text: 'Intervalo Curto',
    class: 'short-break',
  },
}

export { TYPES_CHIPS }
