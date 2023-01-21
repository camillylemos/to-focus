import { Route, Routes } from 'react-router-dom'
import { HomeScreen } from './ui/screen'
import { createTheme, ThemeProvider } from '@mui/material'
import { Footer, Header } from './ui/components'

import './App.scss'

const theme = createTheme({
  palette: {
    primary: {
      main: '#F29166',
    },
  },
})

function App() {
  return (
    <div className="App">
      <ThemeProvider theme={theme}>
        <Header />
        <Routes>
          <Route path="/" element={<HomeScreen />} />
        </Routes>
        <Footer />
      </ThemeProvider>
    </div>
  )
}

export default App
