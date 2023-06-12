import { useNavigate } from 'react-router-dom'
import { RoutesConfigGlobal, useGlobalToken } from '@contexts'
import { Button, Tooltip } from '@mui/material'
import {
  CollectionsBookmarkRounded,
  FormatListBulletedRounded,
  GridViewRounded,
  Insights,
  TimerOutlined,
} from '@mui/icons-material'

import './menu.style.scss'

const style = {
  borderRadius: 5,
  margin: 0.4,
  width: 60,
  height: 60,
}

const Menu = ({ handleClickModal }) => {
  const [, setRoutesConfig] = RoutesConfigGlobal()
  const [token] = useGlobalToken()

  const navigate = useNavigate()

  const handleClick = screen => {
    setRoutesConfig(screen)
  }

  const renderButtons = () => (
    <>
      <Button
        variant="contained"
        onClick={() => handleClick('PomodoroScreen')}
        sx={style}
        color="primary"
      >
        {<TimerOutlined className="pomodoro__icon" />}
      </Button>

      <Button
        variant="contained"
        onClick={() => handleClick('EisenhowerMatrixScreen')}
        sx={style}
        color="primary"
      >
        {<GridViewRounded className="matrix__icon" />}
      </Button>

      <Button variant="contained" onClick={() => handleClickModal()} sx={style} color="primary">
        {<CollectionsBookmarkRounded className="albun__icon" />}
      </Button>

      <Button
        variant="contained"
        onClick={() => handleClick('TaskScreen')}
        sx={style}
        color="primary"
      >
        {<FormatListBulletedRounded className="list__icon" />}
      </Button>

      <Button
        variant="contained"
        onClick={() => handleClick('RelatorioScreen')}
        sx={style}
        color="primary"
      >
        {<Insights className="graphic__icon" />}
      </Button>
    </>
  )

  const renderButtonsWithoutAuthentication = () => (
    <>
      <Button
        variant="contained"
        onClick={() => handleClick('PomodoroScreen')}
        sx={style}
        color="primary"
      >
        {<TimerOutlined className="pomodoro__icon" />}
      </Button>

      <Tooltip
        title="Crie uma conta para acessar a Matriz de Eisenhower"
        placement="left"
        arrow="true"
      >
        <Button
          variant="contained"
          onClick={() => navigate('/cadastro')}
          sx={style}
          color="primary"
        >
          {<GridViewRounded className="matrix__icon" />}
        </Button>
      </Tooltip>

      <Tooltip
        title="Crie uma conta para ganhar conquistas em finalizar seus pomodoros"
        placement="left"
        arrow="true"
      >
        <Button
          variant="contained"
          onClick={() => navigate('/cadastro')}
          sx={style}
          color="primary"
        >
          {<CollectionsBookmarkRounded className="albun__icon" />}
        </Button>
      </Tooltip>

      <Tooltip title="Crie uma conta para acessar a Lista de Tarefas" placement="left" arrow="true">
        <Button
          variant="contained"
          onClick={() => navigate('/cadastro')}
          sx={style}
          color="primary"
        >
          {<FormatListBulletedRounded className="list__icon" />}
        </Button>
      </Tooltip>

      <Tooltip
        title="Crie uma conta para visualizar suas estátisticas"
        placement="left"
        arrow="true"
      >
        <Button
          variant="contained"
          onClick={() => navigate('/cadastro')}
          sx={style}
          color="primary"
        >
          {<Insights className="graphic__icon" />}
        </Button>
      </Tooltip>
    </>
  )

  return (
    <aside className="menu">{token ? renderButtons() : renderButtonsWithoutAuthentication()}</aside>
  )
}

export { Menu }
