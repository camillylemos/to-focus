import { useEffect, useState, useCallback } from 'react'
import { Button, IconButton } from '@mui/material'
import { Delete, Edit } from '@mui/icons-material'
import { Checkbox, Form, Input, Select, Textarea } from '@components'
import { useTask } from '@hooks'
import { FORM_DATA_INITIAL } from './form-data.constant'

import './task.style.scss'

const TaskScreen = () => {
  const [taskList, setTaskList] = useState()
  const [formData, setFormData] = useState({ ...FORM_DATA_INITIAL })

  const { getTasks, createTask, updateTask, deleteTask } = useTask()

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

  const handleChangeEditTask = ({ task }) => {
    setFormData({
      priority: { ...formData.priority, value: task.prioridade },
      description: { ...formData.description, value: task.descricao },
      title: { ...formData.title, value: task.titulo },
      task,
    })
  }

  const renderTasks = () => {
    if (taskList) {
      return Object.values(taskList)?.map(tasks =>
        tasks?.map(task => {
          const value = task.estaRealizado ? { checked: true } : { checked: false }

          return (
            <li key={task.id}>
              <Checkbox
                value={value}
                handleChange={() => handleChangeTask({ task, check: true })}
              />
              <div>{task.titulo}</div>
              <div>{task.descricao}</div>
              <div>{task.prioridade}</div>

              <IconButton onClick={() => handleChangeEditTask({ task })}>
                <Edit />
              </IconButton>

              <IconButton onClick={() => handleChangeDeleteTask({ id: task.id })}>
                <Delete />
              </IconButton>
            </li>
          )
        })
      )
    }
  }

  const handleSubmit = async ({ isValid, values }) => {
    if (formData.task) {
      const updateTask = {
        ...formData.task,
        descricao: values.description,
        prioridade: values.priority,
        titulo: values.title,
      }

      handleChangeTask({ task: updateTask })
      setFormData({ ...FORM_DATA_INITIAL })
    } else {
      if (isValid && values) {
        const data = {
          descricao: values.description,
          prioridade: values.priority,
          titulo: values.title,
        }

        const resultado = await createTask(data)

        if (resultado) {
          setFormData({ ...FORM_DATA_INITIAL })
          getTaskList()
        }
      }
    }
  }

  const handleChange = event => {
    const { name, value } = event.target

    //TODO
    //add validação
    //CampoFormulario

    setFormData(formData => ({
      ...formData,
      [name]: {
        ...formData[name],
        value,
        name,
      },
    }))
  }

  return (
    <section>
      <Form onSubmit={handleSubmit} formData={formData}>
        <Input formData={formData.title} handleChange={handleChange} />
        <Select formData={formData.priority} handleChange={handleChange} />
        <Textarea formData={formData.description} handleChange={handleChange} />
        <Button type="submit">Salvar</Button>
      </Form>

      <ul>{renderTasks()}</ul>
    </section>
  )
}

export { TaskScreen }
