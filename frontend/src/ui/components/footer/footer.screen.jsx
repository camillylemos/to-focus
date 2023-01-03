import { Headphones, LocalFireDepartment } from '@mui/icons-material'
import { IconButton } from '@mui/material'

import './footer.style.scss'

const Footer = () => {
    return (
        <footer className="footer">
            <div className="footer__config">
                <IconButton aria-label="música" sx={{ p: 0.5 }}>
                    <Headphones sx={{ color: '#2E7F7B', fontSize: 27 }} />
                </IconButton>

                <IconButton aria-label="música" sx={{ p: 0.5 }}>
                    <LocalFireDepartment sx={{ color: '#F29166', fontSize: 27 }} />
                </IconButton>
            </div>
        </footer>
    )
}

export { Footer }
