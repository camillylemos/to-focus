import { arrow } from '@assets'
import { COLECAO_FIGURAS } from '@constants'
import { Dialog } from '@mui/material'
import { useEffect, useState } from 'react'
import { TituloPagina } from '../titulo-pagina/titulo-pagina.componente'

import './modal-colecao.style.scss'

const dialogStyle = {
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
  '& .MuiPaper-root': {
    backgroundColor: '#D9D9D9',
    maxWidth: '800px',
    display: 'flex',
    alignItems: 'center',
    padding: '0 0 30px',
    borderRadius: '10px',
  },
}

const ModalColecao = ({ open, handleClose, colecao }) => {
  const [page, setPage] = useState(colecao?.colecao.length - 1 || 0)

  useEffect(() => {
    setPage(colecao?.colecao.length - 1 || 0)
  }, [colecao])

  const handleClickPaginaMuralVoltar = () => {
    setPage(page - 1)
  }

  const handleClickPaginaMuralAvancar = () => {
    setPage(page + 1)
  }

  const renderizarMensagem = ({ colecao }) => {
    if (colecao?.mensagem) {
      return (
        <div className={`modal-colecao__mensagem`}>
          {/* <img
          src={iconeExclamacaoComentarioBranco}
          alt="ícone de comentário com um ponto de exclamação dentro"
        /> */}
          <span>{colecao.mensagem}</span>
        </div>
      )
    }
  }

  return colecao ? (
    <Dialog className="modal-colecao" open={open} onClose={handleClose} sx={dialogStyle}>
      <TituloPagina titulo={'Coleção de Murais'} className="modal-colecao__titulo" />
      <div className="modal-colecao__container">
        {colecao?.colecao.length > 0 && page > 0 ? (
          <button
            onClick={handleClickPaginaMuralVoltar}
            className="modal-colecao__flecha modal-colecao__flecha--esquerda"
          >
            <img src={arrow} alt="" />
          </button>
        ) : (
          <button className="modal-colecao__flecha--invisivel"></button>
        )}

        <div className="modal-colecao__mural">
          <div className="modal-colecao__mural__circulo"></div>

          <div>
            <span className="modal-colecao__mural__titulo">{colecao?.colecao[page].album}</span>

            <div className="modal-colecao__figuras">
              {colecao?.colecao[page].figuras.map(({ id, nome, isPremium }) => {
                const premiumClass = isPremium ? '--premium' : ''
                return (
                  <img
                    key={id}
                    className={`modal-colecao__figura${premiumClass}`}
                    src={COLECAO_FIGURAS[colecao?.colecao[page].album][nome]}
                    alt=""
                  />
                )
              })}
            </div>
          </div>
        </div>

        {colecao?.colecao.length - 1 !== page ? (
          <button
            onClick={handleClickPaginaMuralAvancar}
            className="modal-colecao__flecha modal-colecao__flecha--direita"
          >
            <img src={arrow} alt="" />
          </button>
        ) : (
          <button className="modal-colecao__flecha--invisivel"></button>
        )}
      </div>

      {renderizarMensagem({ colecao })}
    </Dialog>
  ) : null
}

export { ModalColecao }
