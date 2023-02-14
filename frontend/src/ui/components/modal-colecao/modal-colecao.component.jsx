import { Dialog } from '@mui/material'
import './modal-colecao.style.scss'

const ModalColecao = ({ open, handleClose, colecao }) => {
  return (
    <Dialog open={open} onClose={handleClose}>
      {colecao?.mensagem}

      <body>
        <div id="wrapper">
          <div id="container">
            <section class="open-book">
              <header>
                <h1>CWI Software</h1>
                <h6>Projeto Crescer</h6>
              </header>
              <article></article>
              <footer>
                <ol id="page-numbers">
                  <li>1</li>
                  <li>2</li>
                </ol>
              </footer>
            </section>
          </div>
        </div>
      </body>
    </Dialog>
  )
}

export { ModalColecao }
