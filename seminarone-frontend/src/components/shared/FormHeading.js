import "./formheading.css";

const FormHeading = ({ title, subtitle }) => {
  return (
    <div className="heading-container">
      <div className="heading">{title}</div>
      <hr class="separator" />
    </div>
  );
};

export default FormHeading;
