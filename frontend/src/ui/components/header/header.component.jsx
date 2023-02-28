import { logo } from '@assets'
import { AccountCircle, Logout } from '@mui/icons-material'
import { IconButton, ListItemIcon, Menu, MenuItem } from '@mui/material'

import './header.style.scss'

const Header = () => {
  return (
    <header className="header">
      <img className="header__logo" src={logo} alt="" />
      <div className="header__icon">
        <IconButton aria-label="perfil">
          <AccountCircle sx={{ fontSize: 50 }} color="secondary" />
        </IconButton>

        {/* <Menu>
          <MenuItem open={true} onClick={() => console.log('oi')}>
            <ListItemIcon>
              <Logout fontSize="small" />
            </ListItemIcon>
            Logout
          </MenuItem>
        </Menu> */}
      </div>
    </header>
  )
}

export { Header }
