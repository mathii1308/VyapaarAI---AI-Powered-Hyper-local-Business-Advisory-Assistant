import React from 'react';

export default function Footer({ onNavigate }) {
  return (
    <footer className="footer">
      <div className="footer-content">
        <div className="footer-brand">
          <div className="brand-logo">
            <div className="brand-icon">⚡</div>
            <span>VyapaarAI</span>
          </div>
          <p className="footer-desc">
            AI-Powered Hyper-Local Business Advisory & Financial Structuring Assistant for Rural Micro-Entrepreneurs.
          </p>
        </div>

        <div className="footer-links">
          <div className="link-group">
            <h4>Platform</h4>
            <ul>
              <li><a href="#hero">Overview</a></li>
              <li><a href="#problem">Problem & Solution</a></li>
              <li><a href="#how-it-works">How It Works</a></li>
              <li><a href="#capabilities">AI Engine</a></li>
            </ul>
          </div>

          <div className="link-group">
            <h4>Services</h4>
            <ul>
              <li><button onClick={() => onNavigate('profile')} className="text-btn">Business Profile</button></li>
              <li><button onClick={() => onNavigate('profile')} className="text-btn">Financial Analysis</button></li>
              <li><button onClick={() => onNavigate('profile')} className="text-btn">Credit Assessment</button></li>
            </ul>
          </div>

          <div className="link-group">
            <h4>Technology</h4>
            <ul>
              <li><span>React 18 + Vite</span></li>
              <li><span>Spring Boot 3.2</span></li>
              <li><span>Spring Data JPA</span></li>
              <li><span>REST APIs</span></li>
            </ul>
          </div>
        </div>
      </div>

      <div className="footer-bottom">
        <p>© {new Date().getFullYear()} VyapaarAI. Empowering Rural Micro-Enterprises.</p>
        <p className="disclaimer">Designed for rural business structuring & bank credit readiness assessment.</p>
      </div>
    </footer>
  );
}
