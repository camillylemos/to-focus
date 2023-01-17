import { useCallback, useEffect, useState } from 'react'
import { Delete } from '@mui/icons-material'
import { IconButton } from '@mui/material'
import { Checkbox } from '@components'
import { PRIORITY } from '@constants'
import { useTask } from '@hooks'

import './eisenhower-matrix.style.scss'

const EisenhowerMatrixScreen = () => {
  const [taskList, setTaskList] = useState()

  const { getTasks, updateTask, deleteTask } = useTask()

  const getTaskList = useCallback(async () => {
    const resultado = await getTasks()

    if (resultado) {
      setTaskList(resultado)
    }
  }, [getTasks])

  useEffect(() => {
    getTaskList()
  }, [getTaskList])

  const handleChangeTask = async ({ task, check }) => {
    const data = check ? { ...task, estaRealizado: !task.estaRealizado } : task

    const resultado = await updateTask({ data, id: task.id })

    if (resultado) {
      getTaskList()
    }
  }

  const handleChangeDeleteTask = async ({ id }) => {
    const resultado = await deleteTask(id)

    if (resultado) {
      getTaskList()
    }
  }

  const renderTaskItem = key => {
    return taskList[key].map(task => {
      const value = task.estaRealizado ? { checked: true } : { checked: false }

      return (
        <li className="eisenhower-matrix__item" key={task.id}>
          <Checkbox value={value} handleChange={() => handleChangeTask({ task, check: true })} />
          <div>{task.titulo}</div>
          <div>{task.descricao}</div>

          {/* <IconButton onClick={() => handleChangeEditTask({ task })}>
                        <Edit />
                    </IconButton> */}

          <IconButton onClick={() => handleChangeDeleteTask({ id: task.id })}>
            <Delete />
          </IconButton>
        </li>
      )
    })
  }

  const renderTaskList = () => {
    if (taskList) {
      return PRIORITY.map(({ name, key }, index) => {
        return (
          <ul className="eisenhower-matrix__list" key={index}>
            <div>{name}</div>
            {renderTaskItem(key)}
          </ul>
        )
      })
    }
  }

  return <section className="eisenhower-matrix">{renderTaskList()}</section>
}

export { EisenhowerMatrixScreen }
