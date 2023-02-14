import { Button, Dialog, IconButton } from '@mui/material'
import { Delete } from '@mui/icons-material'
import { Form, Input } from '@components'

import './modal.style.scss'
import { useState } from 'react'

const ModalComponent = ({
  formData,
  handleSubmit,
  handleChange,
  handleClick,
  handleClickDelete,
  pomodoroSettingsList,
}) => {
  const [openModal, setOpenModal] = useState(true)

  const handleClose = () => {
    setOpenModal(false)
  }

  return (
    <Dialog open={openModal} onClose={handleClose}>
      <Form onSubmit={handleSubmit} formData={formData}>
        <Input {...formData.name} handleChange={handleChange} />
        <Input {...formData.pomodoro} handleChange={handleChange} />
        <Input {...formData.shortBreak} handleChange={handleChange} />
        <Input {...formData.longBreak} handleChange={handleChange} />
        <Button type="submit">Salvar</Button>
      </Form>

      <div>
        {!!pomodoroSettingsList &&
          pomodoroSettingsList?.map(
            ({
              id,
              nomeCategoria,
              tempoFoco,
              tempoIntervaloCurto,
              tempoIntervaloLongo,
              isVisivel,
            }) =>
              !!isVisivel && (
                <div key={id}>
                  <Button
                    onClick={() =>
                      handleClick({
                        id,
                        nomeCategoria,
                        tempoFoco,
                        tempoIntervaloCurto,
                        tempoIntervaloLongo,
                      })
                    }
                  >
                    <div>{nomeCategoria}</div>
                    <div>{tempoFoco}</div>
                    <div>{tempoIntervaloCurto}</div>
                    <div>{tempoIntervaloLongo}</div>
                  </Button>
                  <IconButton onClick={() => handleClickDelete(id)}>
                    <Delete />
                  </IconButton>
                </div>
              )
          )}
      </div>
    </Dialog>
  )
}

export { ModalComponent }
