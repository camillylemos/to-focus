import {LocalFireDepartment, DarkMode } from '@mui/icons-material'
import { IconButton } from '@mui/material'

import './footer.style.scss'

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer__config">
        <IconButton aria-label="música" sx={{ p: 0.5 }}>
          <DarkMode sx={{ fontSize: 27 }} color="secondary" />
        </IconButton>

        <IconButton aria-label="música" sx={{ p: 0.5 }}>
          <LocalFireDepartment sx={{ fontSize: 27 }} color="primary" />
        </IconButton>
      </div>
    </footer>
  )
}

export { Footer }
