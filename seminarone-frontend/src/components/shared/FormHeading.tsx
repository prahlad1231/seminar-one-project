import "./formheading.css";

interface IFormHeadingProps {
  title: string;
  subtitle?: string; // making subtitle prop optional
}

const FormHeading = ({ title, subtitle }: IFormHeadingProps) => {
  return (
    <div className="heading-container">
      <div className="heading">{title}</div>
      <hr className="separator" />
    </div>
  );
};

export default FormHeading;
