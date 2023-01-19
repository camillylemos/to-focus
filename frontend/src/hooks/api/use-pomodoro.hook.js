/* eslint-disable react-hooks/exhaustive-deps */
import { useMemo } from "react"
import { useAxios } from "./use-axios"

const usePomodoro = () => {
    const { get } = useAxios()

    const carregarConfiguracaoPomodoros = () => get('/pomodoro')

    return useMemo(
        () => ({
            carregarConfiguracaoPomodoros
        }),
        []
    )
}

export { usePomodoro }
