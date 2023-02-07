import { Route, Routes } from 'react-router-dom'
import { createTheme, ThemeProvider, useTheme } from '@mui/material'
import { RoutesConfigGlobalProvider } from '@contexts'
import { Footer, Header } from '@components'
import { HomeScreen } from '@screen'
import CssBaseline from '@mui/material/CssBaseline';
import * as React from 'react';
import IconButton from '@mui/material/IconButton';
import Box from '@mui/material/Box';
import Brightness4Icon from '@mui/icons-material/Brightness4';
import Brightness7Icon from '@mui/icons-material/Brightness7';


import './App.scss'

/* const ColorModeContext = React.createContext({ toggleColorMode: () => {} }) */;



 const defaultTheme = createTheme({
  palette: {
    primary: {
      main: '#F29166',
    },
    secondary: {
      main: '#2E7F7B',
    },
    third: {
      main: '#808080',
    },
    background: {
      default: '#F5F5F5',
    },
  },
})



const darkTheme = createTheme({
  palette: {
    primary: {
      main: '#2e2826',
    },
    secondary: {
    main: '#768cb4',
    },
    third: {
      main: '#974081',
    },
    background: {
      default: '#12131244'
    },
  },
}); 


/* function App(){
const theme = useTheme();
const colorMode = React.useContext(ColorModeContext);
return(
  <RoutesConfigGlobalProvider>
  <Box className='App'
  <Routes>
  <Route path='/' element={<HomeScreen/>}
  </Routes>
  sx={{
    display: 'flex',
    width: '100%',
    alignItems: 'center',
    justifyContent: 'center',
    bgcolor: 'background.default',
    color: 'text.primary',
    borderRadius: 1,
    p: 3,
  }} >
{theme.palette.mode} mode 
<IconButton sx={{m1: 1}} onClick={colorMode.toggleColorMode} color="inherit">
  {theme.palette.mode == 'dark' ? <Brightness7Icon/> : <Brightness4Icon/>}
</IconButton>

</Box>
</RoutesConfigGlobalProvider>
);
}

export default function ToggleColorMode() {
  const [mode, setMode] = React.useState('light');
  const colorMode = React.useMemo(
    () => ({
      toggleColorMode: () => {
        setMode((prevMode) => (prevMode === 'light' ? 'dark' : 'light'));
      },
    }),
    [],
  );

  const theme = React.useMemo(
    () =>
      createTheme({
        palette: {
          mode,
        },
      }),
    [mode],
  );

  return (
    <ColorModeContext.Provider value={colorMode}>
      <ThemeProvider theme={theme}>
        <App />
      </ThemeProvider>
    </ColorModeContext.Provider>
  );
} */

 function App() {
  return (
    <div className="App">
      <RoutesConfigGlobalProvider>
        <ThemeProvider theme={defaultTheme}>
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
