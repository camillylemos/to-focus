import { Button, Dialog, Icon, IconButton, Typography } from '@mui/material'
import { CenterFocusStrong, Delete } from '@mui/icons-material'
import { Form, Input } from '@components'
import CloseIcon from '@mui/icons-material/Close'
import { spacing } from '@mui/system'
import Box from '@mui/material/Box'
import AccessAlarmIcon from '@mui/icons-material/AccessAlarm'
import TaskAltIcon from '@mui/icons-material/TaskAlt'
import './modal.style.scss'

const ModalComponent = ({
  formData,
  handleSubmit,
  handleChange,
  handleClick,
  handleClickDelete,
  handleClickClose,
  pomodoroSettingsList,
  open,
}) => {
  return (
    <Dialog
      open={open}
      onClose={handleClickClose}
      PaperProps={{
        sx: {
          width: '40%',
          height: '100%',
          borderRadius: '8px',
        },
      }}
    >
      <Box>
        <Typography
          color="secondary"
          sx={{ display: 'flex', justifyContent: 'flex-start', ml: 1, mt: 2, fontSize: 16 }}
        >
          Configurações
          <Icon
            size="small"
            sx={{
              display: 'flex',
              justifyContent: 'flex-start',
              fontSize: 50,
              ml: 1,
              mb: -1,
              color: 'primary',
            }}
          >
            {<AccessAlarmIcon />}
          </Icon>{' '}
        </Typography>
      </Box>

      <Form onSubmit={handleSubmit} formData={formData}>
        <Input
          {...formData.name}
          handleChange={handleChange}
          sx={{ mr: 1, ml: 1, display: 'flex', justifyContent: 'center' }}
        />
        <Box display="flex" justifyContent="space-between">
          <Input {...formData.pomodoro} handleChange={handleChange} sx={{ width: '30%', mt:1, mr: 0, ml:1 }} />
          <Input {...formData.shortBreak} handleChange={handleChange} sx={{ width: '30%', mt:1, mr: 0, ml:1 }} />
          <Input {...formData.longBreak} handleChange={handleChange} sx={{ width: '30%', mt:1, mr: 1, ml:1 }} />
        </Box>
        <Box textAlign="center" sx={{ mt: 2 }}>
          <Button variant="contained" color="secondary" type="submit">
            OK
          </Button>
        </Box>
      </Form>

      <Box>
        <Typography
          color="secondary"
          sx={{ display: 'flex', justifyContent: 'flex-start', ml: 1, mt: 2, fontSize: 16 }}
        >
          Pomodoros Salvos 
          <Icon
            size="small"
            sx={{
              display: 'flex',
              justifyContent: 'flex-start',
              fontSize: 50,
              ml: 1,
              mb: -3,
              color: 'secondary',
            }}
          >
            {<TaskAltIcon />}
          </Icon>
        </Typography>
      </Box>

      <Box>
        <Box>
          <Box  width="100%" display="flex" justifyContent="space-between">
            <Typography
              sx={{ display: 'flex', justifyContent: 'flex-start', ml: 1, mt: 1, mb:2}}
              color="gray"
            >
              Nome
            </Typography>
            <Typography
              sx={{ display: 'flex', justifyContent: 'flex-start', ml: 1, mt: 1, mb:2 }}
              color="gray"
            >
              Tempo de Foco
            </Typography>
            <Typography
              sx={{ display: 'flex', justifyContent: 'flex-start', ml: 1, mt: 1, mb:2 }}
              color="gray"
            >
              Intervalo Curto
            </Typography>
            <Typography
              sx={{ display: 'flex', justifyContent: 'flex-start', ml: 1, mr:7,  mt: 1, mb:2 }}
              color="gray"
            >
              Intervalo Longo
            </Typography>
          </Box>
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
                  <Box key={id}>
                    <button
                      className="button__config__time__focus"
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
                    </button>
                    <IconButton className="icon__delete__pomodoro"  onClick={() => handleClickDelete(id)}>
                      <Delete />
                    </IconButton>
                  </Box>
                )
            )}
        </Box>
      </Box>
    </Dialog>
  )
}

export { ModalComponent }
