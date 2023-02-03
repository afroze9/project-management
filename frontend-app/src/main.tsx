import { Auth0Provider } from '@auth0/auth0-react';
import * as React from 'react';
import ReactDOM from 'react-dom/client';
import { ThemeProvider } from '@emotion/react';
import { CssBaseline } from '@mui/material';
import theme from './theme';
import App from './App';

ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
  <React.StrictMode>
    <Auth0Provider
      domain="afrozeprojectmanagement.us.auth0.com"
      clientId="mIUsoezCpUuf9zcm7py5syzgtVvNQTvD"
      authorizationParams={{
        redirect_uri: window.location.origin,
        scope: 'read:current_user update:current_user_metadata',
        audience: 'https://afrozeprojectmanagement.us.auth0.com/api/v2/',
      }}
    >
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <App />
      </ThemeProvider>
    </Auth0Provider>
  </React.StrictMode>,
);
