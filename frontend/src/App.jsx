import { Route, Routes } from 'react-router-dom'
import { createTheme, ThemeProvider } from '@mui/material'
import { RoutesConfigGlobalProvider } from '@contexts'
import { Footer, Header } from '@components'
import { HomeScreen } from '@screen'

import './App.scss'

const theme = createTheme({
  palette: {
    primary: {
      main: '#F29166',
    },
    secondary: {
      main: '#2E7F7B',
    },
    third: {
      main: '#808080'
    },
    background: {
      default: '#F5F5F5',
    }
  },
})

function App() {
  return (
    <div className="App">
      <RoutesConfigGlobalProvider>
        <ThemeProvider theme={theme}>
          <Header />
          <Routes>
            <Route path="/" element={<HomeScreen />} />
          </Routes>
          <Footer />
        </ThemeProvider>
      </RoutesConfigGlobalProvider>
    </div>
  )
}

export default App
