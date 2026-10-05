import React from 'react';

const SUGGESTED_QUESTIONS = [
  "How is my business performing?",
  "Should I expand my business?",
  "Can I afford a loan?",
  "How can I improve my profit?",
  "What should I do with my available investment?"
];

export default function SuggestedQuestions({ onSelectQuestion, disabled }) {
  return (
    <div className="suggested-questions-container">
      <span className="suggested-label">💡 Suggested Advisory Questions:</span>
      <div className="suggested-pills-row">
        {SUGGESTED_QUESTIONS.map((q, idx) => (
          <button
            key={idx}
            className="suggested-question-btn"
            onClick={() => onSelectQuestion(q)}
            disabled={disabled}
          >
            {q}
          </button>
        ))}
      </div>
    </div>
  );
}
