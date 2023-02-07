import { album, graphic, listCheck, matrix, note } from '@assets'
import { RoutesConfigGlobal } from '@contexts'
import { Button } from '@mui/material'
import { FormatListBulletedRounded, GridViewRounded, CollectionsBookmarkRounded, TimerOutlined, Insights, StickyNote2Outlined } from '@mui/icons-material';
import './menu.style.scss'

const Menu = () => {
  const [, setRoutesConfig] = RoutesConfigGlobal()

  const handleClick = screen => {
    setRoutesConfig(screen)
  }

  return (
    <aside className="menu">

   <Button variant="contained" onClick={() => handleClick('PomodoroScreen')}
          sx={{
            borderRadius: 5,
            margin: 0.4,
            width: 60,
            height: 60,
          }}
          color="primary"
        >
         {<TimerOutlined className='pomodoro__icon'/>} 
        </Button>

        <Button variant="contained" onClick={() => handleClick('EisenhowerMatrixScreen')}
          sx={{
            borderRadius: 5,
            margin: 0.4,
            width: 60,
            height: 60,
          }}
          color="primary"
        >
        {<GridViewRounded className='matrix__icon'/>} 
        </Button>

        <Button variant="contained" onClick={() => handleClick('TaskScreen')}
          sx={{
            borderRadius: 5,
            margin: 0.4,
            width: 60,
            height: 60,

          }}
          color="primary"
        >
         {<FormatListBulletedRounded className='list__icon'/>} 
        </Button>

        <Button variant="contained" 
          sx={{
            borderRadius: 5,
            margin: 0.4,
            width: 60,
            height: 60,
          }}
          color="primary"
        >
         {<CollectionsBookmarkRounded className='albun__icon'/>}  
        </Button>

        <Button variant="contained" 
          sx={{
            borderRadius: 5,
            margin: 0.4,
            width: 60,
            height: 60,
          }}
          color="primary"
        >
         {<Insights className='graphic__icon'/>}  
        </Button> 


        <Button variant="contained" 
          sx={{
            borderRadius: 5,
            margin: 0.4,
            width: 60,
            height: 60,
          }}
          color="primary"
        >
         {<StickyNote2Outlined className='notes__icon'/>}  
        </Button>

    
    </aside>
  )
}

export { Menu }
