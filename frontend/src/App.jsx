import { Route, Routes } from 'react-router-dom'
import { createTheme, ThemeProvider } from '@mui/material'
import { HomeScreen } from '@screen'
import { Footer, Header } from '@components'

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
