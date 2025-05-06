import { createRoot } from "react-dom/client";
import "./index.css";
import { BrowserRouter } from "react-router";
import AppRoutes from "./config/Routes.jsx";
import { Toaster } from "react-hot-toast";
import { Stomp } from "@stomp/stompjs";
import { ChatProvider } from "./context/ChatContext.jsx";

createRoot(document.getElementById("root")).render(
  <BrowserRouter>
       <Toaster position="top-right"/>
       <ChatProvider>
      <AppRoutes />
       </ChatProvider>
  </BrowserRouter>
)
