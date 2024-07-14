import { createContext, useContext, useEffect, useState } from "react";
import { AuthService } from "../../services/AuthService";

const AuthContext = createContext();

const authService = new AuthService();

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(null);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    // console.log(user);
    if (user && user.jwtToken) {
      console.log(user.jwtToken);
      authService.validateToken(user.jwtToken).then((isExpired) => {
        console.log(`Token Expired: ${isExpired}`);
        if (isExpired) {
          setIsAuthenticated(false);
          localStorage.removeItem("user");
        } else {
          setIsAuthenticated(true);
        }
      });
    } else {
      setIsAuthenticated(false);
    }
  }, []);

  const login = () => setIsAuthenticated(true);
  const logout = () => {
    authService.logout();
    setIsAuthenticated(false);
  };

  if (isAuthenticated === null) {
    console.log("Not Authenticated!");
    return null;
  }

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, setIsAuthenticated, login, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
