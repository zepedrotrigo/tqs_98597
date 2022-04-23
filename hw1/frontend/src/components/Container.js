import { useInView } from 'react-intersection-observer';
import "./Container.css";

function Container({ children, extClass }) {
    const { ref, inView } = useInView({ triggerOnce: true, threshold: 0.3, delay: 500 });
    let animatedClass = extClass + ' animate__animated animate__zoomIn';

    return (
        <div
            ref={ref}
            className={(inView && !extClass.includes("no-anim")) ? animatedClass : extClass}
            style={inView ? { visibility: 'visible' } : { visibility: 'hidden' }}
        >
            {children}
        </div>
    )
}

Container.defaultProps = {    
    extClass: "container"
  }

export default Container;