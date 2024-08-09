import {
  createContext,
  ReactNode,
  useContext,
  useEffect,
  useState,
} from "react";
import { AuthService } from "../../services/AuthService";

interface IAuthContextType {
  isAuthenticated: boolean | null;
  login: () => void;
  logout: () => void;
  setIsAuthenticated: (value: boolean) => void;
}

const AuthContext = createContext<IAuthContextType | undefined>(undefined);

const authService = new AuthService();

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider = ({ children }: AuthProviderProps) => {
  const [isAuthenticated, setIsAuthenticated] = useState<boolean | null>(null);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user") || "null");
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

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within an AuthProvider!");
  }
  return context;
};
