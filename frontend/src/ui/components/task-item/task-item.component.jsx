import { LocalOffer } from '@mui/icons-material'
import { useEffect, useState } from 'react'
import { Checkbox } from '../checkbox/checkbox.component'

import './task-item.style.scss'

const TaskItem = ({
  task,
  handleChangeTask,
  color,
  name,
  className,
  handleClickSaveAlter,
  isTask,
}) => {
  const [valueTitle, setValueTitle] = useState(task?.titulo)
  const [valueDescription, setValueDescription] = useState(task.descricao)

  useEffect(() => {
    setValueTitle(task?.titulo)
    setValueDescription(task.descricao)
  }, [task])

  const value = task.estaRealizado ? { checked: true } : { checked: false }

  const classRealizado = task.estaRealizado ? '--realizado' : ''

  const classeTipo = isTask ? 'task__item' : 'eisenhower-matrix__item'

  const handleChangeTitle = event => setValueTitle(event.target.value)

  const handleChangeDescription = event => setValueDescription(event.target.value)

  const handleBlurSaveAlter = () => {
    if (valueTitle !== task.titulo || valueDescription !== task.descricao) {
      handleClickSaveAlter((task = { ...task, titulo: valueTitle, descricao: valueDescription }))
    }
  }

  return (
    <li className={classeTipo} key={task.id}>
      <div className="eisenhower-matrix__item__principal">
        <div className={`${classeTipo}__principal__esquerda`}>
          <Checkbox
            value={value}
            handleChange={() => handleChangeTask({ task, check: true })}
            color={color}
          />

          <textarea
            disabled={task.estaRealizado}
            autoComplete="off"
            rows="1"
            spellcheck="false"
            className={`eisenhower-matrix__item__principal__esquerda__texto${classRealizado}`}
            value={valueTitle}
            onChange={handleChangeTitle}
            onBlur={handleBlurSaveAlter}
          />
        </div>

        {/* PRIORITY */}

        {/* onClick={() => handleChangeDeleteTask({ id: task.id })} */}
        {/* <IconButton onClick={console.log('deletar editar sla faça algo')}>
                <Edit />
              </IconButton> */}

        {/* {adicionar X quando tiver hover TODO} */}

        {isTask && (
          <div
            className={`task__item__principal__esquerda__${className} task__item__principal__esquerda${classRealizado}`}
          >
            <span>{name}</span>
            <LocalOffer />
          </div>
        )}
      </div>

      {task.descricao && (
        <textarea
          disabled={task.estaRealizado}
          autoComplete="off"
          rows="1"
          spellcheck="false"
          className={`${classeTipo}__descricao${classRealizado} ${classeTipo}__descricao__${className}`}
          value={valueDescription}
          onChange={handleChangeDescription}
          onBlur={handleBlurSaveAlter}
        />
      )}
    </li>
  )
}

export { TaskItem }
