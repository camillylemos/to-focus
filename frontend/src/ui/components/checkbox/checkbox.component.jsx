/* eslint-disable react/jsx-pascal-case */
import { CheckCircle, CircleOutlined } from '@mui/icons-material'
import { Checkbox as _Checkbox } from '@mui/material'

const Checkbox = ({ value, handleChange, color }) => {
  return (
    <_Checkbox
      icon={<CircleOutlined />}
      checkedIcon={<CheckCircle />}
      {...value}
      onChange={handleChange}
      sx={{
        '&.Mui-checked': {
          color: color /* define a cor da borda do checkbox quando não estiver selecionado */,
          '&::before': {
            color: color /* define a cor do ícone do checkbox quando estiver selecionado */,
          },
        },

        color: { color },
      }}
    />
  )
}

export { Checkbox }
