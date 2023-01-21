import { AccountCircle } from '@mui/icons-material'
import { IconButton } from '@mui/material'

import './header.style.scss'

const Header = () => {
  return (
    <header className="header">
      <div className="header__icon">
        <IconButton aria-label="perfil">
          <AccountCircle sx={{ fontSize: 50 }} color="secondary" />
        </IconButton>
      </div>
    </header>
  )
}

export { Header }
