import { PRIORITY } from '@constants'

const FORM_DATA_INITIAL = {
  title: {
    name: 'title',
    label: 'Título',
    value: '',
  },
  description: {
    name: 'description',
    placeholder: 'Descrição',
    value: '',
  },
  priority: {
    name: 'priority',
    label: 'Prioridade',
    options: PRIORITY,
    value: 'NAO_URGENTE_NAO_IMPORTANTE',
  },
}

export { FORM_DATA_INITIAL }
