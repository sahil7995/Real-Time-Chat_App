import { Children} from "react"
import { createContext, useContext, useState } from "react"

const ChatContext=createContext();

export const ChatProvider=({children})=>{

    const [roomId,setRoomId]=useState('')
    const[currentUser,setCurrentUser]=useState('')
    const [connected,setConnected]=useState(false)

    return <ChatContext.Provider 
    value={{roomId,currentUser,connected,setRoomId,setCurrentUser,setConnected}}>
            {children}
        </ChatContext.Provider>
}
export const useChatContext=()=> useContext(ChatContext)

