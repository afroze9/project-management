import { Auth0ContextInterface, useAuth0 } from '@auth0/auth0-react';
import React from 'react';
import {
  createBrowserRouter,
  createRoutesFromElements,
  Navigate,
  Route,
  RouterProvider,
} from 'react-router-dom';

import { AppLayout } from './components/AppLayout';
import CompaniesPage from './pages/CompaniesPage';
import HomePage from './pages/HomePage';
import ProductsPage from './pages/ProductsPage';
import { ApplicationUser } from './types/ApplicationUser';

const Protected = ({
  isAuthenticated,
  children,
}: {
  isAuthenticated: boolean;
  children: any;
}) => {
  if (!isAuthenticated) {
    return <Navigate to="/" replace />;
  }

  return children;
};

function App() {
  const { isAuthenticated }: Auth0ContextInterface<ApplicationUser> = useAuth0();

  const router = createBrowserRouter(
    createRoutesFromElements(
      <Route element={<AppLayout />}>
        <Route path="/" element={<HomePage />} />
        <Route
          path="/products"
          element={
            <Protected isAuthenticated={isAuthenticated}>
              <ProductsPage />
            </Protected>
          }
        />
        <Route
          path="/companies"
          element={
            <Protected isAuthenticated={isAuthenticated}>
              <CompaniesPage />
            </Protected>
          }
        />
      </Route>,
    ),
  );

  return <RouterProvider router={router} />;
}

export default App;
