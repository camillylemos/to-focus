import { Route, Routes } from 'react-router-dom'
import { Alert, createTheme, ThemeProvider } from '@mui/material'
import {
  AlertGlobalProvider,
  RoutesConfigGlobalProvider,
  TokenGlobalProvider,
} from '@contexts'
import { HelmetProvider } from 'react-helmet-async'
import { Footer, Header } from '@components'
import { LoginScreen, CadastroScreen, HomeScreen } from '@screen'

import './App.scss'
import { UserGlobalProvider } from 'contexts/user.context'

const defaultTheme = createTheme({
  palette: {
    primary: {
      main: '#F29166',
    },
    secondary: {
      main: '#2E7F7B',
    },
    tertiary: {
      main: '#808080',
    },
    background: {
      default: '#F5F5F5',
    },
  },
  components: {
    MuiTextarea: {
      defaultProps: {
        disableFocusRipple: true,
      },
    },
  },
})

function App() {
  return (
    <>
      <div className="App">
        <RoutesConfigGlobalProvider>
          <HelmetProvider>
            <AlertGlobalProvider>
              <TokenGlobalProvider>
                <UserGlobalProvider>
                  <ThemeProvider theme={defaultTheme}>
                    <Header />
                    <Routes>
                      <Route path="/" element={<HomeScreen />} exact />
                      <Route path="/login" element={<LoginScreen />} exact />
                      <Route
                        path="/cadastro"
                        element={<CadastroScreen />}
                        exact
                      />
                    </Routes>
                    <Footer />
                  </ThemeProvider>
                </UserGlobalProvider>
              </TokenGlobalProvider>
            </AlertGlobalProvider>
          </HelmetProvider>
        </RoutesConfigGlobalProvider>
      </div>
    </>
  )
}

export default App
