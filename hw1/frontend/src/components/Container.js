import "./Container.css";

function Container({ children, extClass }) {
    return (
        <div className={extClass}>
            {children}
        </div>
    )
}

Container.defaultProps = {    
    extClass: "container"
  }

export default Container;